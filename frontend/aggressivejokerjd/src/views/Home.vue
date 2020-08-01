<template>
  <div class="home">
    <HeaderContain
      :url="`url(`+url+`)`"
      :h1message="h1message"
      :h2message="h2message"
      :h3message="h3message"
    ></HeaderContain>
    <SideBar></SideBar>
    <div class="contain">
      <div class="home_post_list">
        <div class="post-list-wrapper">
          <div class="post_list_container row1">
            <ArticleCard
              v-for="(article,index) in articlelist0"
              :key="index"
              :title="article.title"
              :articleimg="article.pic_url"
              :time="article.date"
              :des="article.des"
              :tags="article.tags"
              :id="article.id"
            ></ArticleCard>
          </div>
          <div class="post_list_container row2">
            <ArticleCard
              v-for="(article,index) in articlelist2"
              :key="index"
              :title="article.title"
              :articleimg="article.pic_url"
              :time="article.date"
              :des="article.des"
              :tags="article.tags"
              :id="article.id"
            ></ArticleCard>
          </div>
          <div class="post_list_container row2">
            <ArticleCard
              v-for="(article,index) in articlelist1"
              :key="index"
              :title="article.title"
              :articleimg="article.pic_url"
              :time="article.date"
              :des="article.des"
              :tags="article.tags"
              :id="article.id"
            ></ArticleCard>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
// @ is an alias to /src
import HeaderContain from "../components/HeaderContain.vue";
import SideBar from "@/components/SideBar.vue";
import ArticleCard from "@/components/ArticleCard.vue";
import axios from "axios";
export default {
  name: "Home",
  data() {
    return {
      h1message:
        "<p>Hello, This is</p><p>AggressiveJokerJD's Blog.</p><p>Author is a newbie.</p>",
      h2message:
        "<p>虽然是一个菜鸡，但是每天还是会异想天开。</p><p>很喜欢《海贼王》，很喜欢玩游戏。</p><p>如果哲学可以填饱肚子，那人们都是哲学家了!</p>",
      h3message: "日本京都桂川水系",
      url:
        "https://pic-bed-1259719447.cos.ap-chengdu.myqcloud.com/%E6%9E%AB%E5%8F%B6%E7%BB%BF%E6%B0%B4%E5%B0%8F%E8%88%9F.jpg",
      articlelist: []
    };
  },
  async created() {
    this.articlelist = await this.getArticleList();
  },
  computed: {
    articlelist1: function() {
      return this.articlelist.filter(function(article) {
        return article.id % 3 === 1;
      });
    },
    articlelist2: function() {
      return this.articlelist.filter(function(article) {
        return article.id % 3 === 2;
      });
    },
    articlelist0: function() {
      return this.articlelist.filter(function(article) {
        return article.id % 3 === 0;
      });
    }
  },
  methods: {
    async getArticleList() {
      var list = [];
      await axios
        .post("http://localhost:8081/article/getArticleList")
        .then(response => {
          list = response.data;
          for (let i = 0; i < list.length; i++) {
            list[i].date = new Date(list[i].date);
          }
        })
        .catch(error => {
          console.log(error);
        });
      return list;
    }
  },
  components: {
    HeaderContain,
    ArticleCard,
    SideBar
  }
};
</script>
<style scoped lang="scss">
.post_container,
.post_list_container {
  display: flex;
  flex-direction: column;
  align-items: center;
}
.post_list_container {
  width: 33.3%;
}
.home_post_list {
  display: flex;
  flex-direction: column;
}
.post-list-wrapper {
  display: flex;
}
.post_container {
  position: relative;
  min-width: 0;
  word-wrap: break-word;
  background-color: transparent;
  background-clip: border-box;
  border: 0 solid rgba(0, 0, 0, 0.125);
  border-radius: 0;
  margin: 1rem;
  padding: 0.5rem;
  cursor: pointer;
  margin-bottom: 1.5rem !important;
  border-bottom: 1px solid rgba(0, 0, 0, 0.1);
}
.contain {
  background: #fff;
  padding: 20px;
  /* width: calc(100% - 200px); */
  margin-left: 180px;
  margin-right: 180px;
  width: auto;
  // background: url(http://ww1.sinaimg.cn/large/88b26e1cgy1ftdstdeacbj212f0d73zv.jpg) top no-repeat;
  // background-size: contain;
}
.article-contain-list {
  display: flex;
  flex-direction: column;
  height: auto;
}
.row1 {
  margin-left: 10px;
}
.row2 {
  left: 350px;
}
</style>
