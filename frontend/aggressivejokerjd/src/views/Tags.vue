<template>
  <div class="tags">
    <div class="tag-cloud">
      <ul>
        <li v-for="(tag,index) in taglist" :key="index">
          <a href="#" @click="tagClick(index)">
            <span class="tag">{{tag}}</span>
          </a>
        </li>
      </ul>
    </div>
    <div class="tag-info-list">
      <div class="tag-info">
        <h3 class="tag-name">{{ tagPreviewList[showtag].tagname }}</h3>
        <ul class="tag-previews">
          <li
            v-for="(item,index) in tagPreviewList[showtag].previews"
            :key="index"
            class="tag-preview"
          >
            <router-link class="article-name" :to="'/article/'+item.title+`,`+item.url">{{ item.title }}</router-link>
            <span class="article-gmtdate">{{getUTCString(item.date)}}</span>
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "Tags",
  props: {
    name: String
  },
  data() {
    return {
      showtag: 0,
      taglist: ["test", "blog", "Git", "diary", "vue"],
      tagPreviewList: [
        {
          tagname: "vue",
          previews: [
            {
              id: 5,
              title: "vue搭建一个舒服的hexo博客",
              date: new Date("2020-07-16 16:00")
            }
          ]
        }
      ]
    };
  },
  async created() {
    this.taglist = await this.getTagList();
    await this.getTagPreviewList();
    this.whichToShow();
  },
  methods: {
    tagClick(index) {
      this.showtag = index;
    },
    getUTCString(date) {
      return date.toDateString();
    },
    whichToShow() {
      if (this.name !== undefined) {
        for (let i = 0; i < this.tagPreviewList.length; i++) {
          if (this.tagPreviewList[i].tagname === this.name) {
            this.showtag = i;
            break;
          }
        }
      }
    },
    async getTagList() {
      var list = [];
      await axios
        .post("http://localhost:8081/tag/getTagList")
        .then((response) => {
          list = response.data;
        })
        .catch((error) => {
          console.log(error);
        });
      return list;
    },
    async getTagPreviewList() {
      this.tagPreviewList.pop();
      await axios
        .post("http://localhost:8081/articletag/getArticleTag")
        .then((response) => {
          var list = response.data;
          for (let i = 0; i < list.length; i++) {
            var element = {
              tagname: list[i].tagname,
              previews: []
            };
            for (let j = 0; j < list[i].previews.length; j++) {
              var article = list[i].previews[j];
              element.previews.push({
                title: article.title,
                date: new Date(article.date),
                url: article.url
              });
            }
            this.tagPreviewList.push(element);
          }
        })
        .catch((error) => {
          console.log(error);
        });
    }
  }
};
</script>
<style lang="scss" scoped>
.tags {
  font-family: itc, ac;
  max-width: 800px;
  width: 800px;
  margin: 0 auto;
  position: relative;
  display: flex;
  justify-content: space-between;
}
.tag-cloud {
  display: flex;
  flex-direction: column;
  width: 40%;
  height: 100vh;
}
.tag-cloud ul {
  list-style: none;
  margin-top: 2rem;
  padding-left: 2px;
  padding-right: 2px;
}
.tag-cloud ul li {
  list-style-type: none;
  margin-right: 0.5rem;
  padding-bottom: 1rem !important;
  box-sizing: border-box;
  display: inline-block;
}
.tag {
  border-radius: 5px;
  font-weight: 600;
  padding: 0 0.5rem;
  margin-right: 1rem;
  float: left;
  margin-bottom: 0.5rem;
}
.tag:not(body) {
  background-color: #ebfffc81;
  color: #00947e;
  font-size: 1.5rem;
  font-family: itc;
}
.tag-info-list {
  width: 60%;
  height: 100vh;
}
.tag-info {
  margin-top: 1em;
}
.tag-name {
  text-align: left;
  font-size: 32px;
}
.tag-name::first-letter {
  color: #42b983;
  font-size: 42px;
}
.tag-previews {
  width: 100%;
  margin-top: 1rem;
  margin-left: 20px;
}
.tag-preview {
  display: flex;
  -ms-flex-pack: justify;
  justify-content: space-between;
  line-height: 4rem;
  border-bottom: 1px solid #ccc;
  cursor: pointer;
}
.article-gmtdate {
  text-align: right;
}
.article-name {
  text-decoration: none;
  color: rgba(80, 78, 78, 0.685);
  text-align: left;
}
.article-name:hover {
  opacity: 0.5;
  border-bottom: 1px solid rgba(65, 231, 190, 0.637);
  color: #1ddfb5;
}
.article-name::before {
  content: "";
  position: absolute;
  width: 15px;
  height: 15px;
  background-color: rgb(99, 97, 97);
  border: 3px solid #1ddfb5;
  border-radius: 50%;
  z-index: 1;
  left: 40%;
  margin-top: 22px;
}
</style>
