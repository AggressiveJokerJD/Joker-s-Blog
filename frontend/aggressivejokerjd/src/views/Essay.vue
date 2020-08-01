<template>
  <div class="home">
    <HeaderContain
      :url="`url(`+url+`)`"
      :h1message="h1message"
      :h2message="h2message"
      :h3message="h3message"
    ></HeaderContain>
    <div class="contain">
      <div class="home_post_list">
        <div class="post-list-wrapper">
          <div class="post_list_container row1">
            <EssayCard
              v-for="(article,index) in articlelist"
              :key="index"
              :title="article.title"
              :articleimg="article.pic_url"
              :time="article.date"
              :des="article.des"
              :tags="article.tags"
              :id="article.id"
            ></EssayCard>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
// @ is an alias to /src
import HeaderContain from "../components/HeaderContain.vue";
import EssayCard from "@/components/EssayCard.vue";
import axios from "axios";
export default {
  name: "Home",
  data() {
    return {
      h1message:
        "<p>杂记</p>",
      h2message:
        "<p>随便记录点琐事，无什么重要的。</p>",
      h3message: "",
      url:
        "https://pic-bed-1259719447.cos.ap-chengdu.myqcloud.com/%E6%B3%B0%E5%9B%BD%E7%94%B2%E7%B1%B3%E5%BA%9C%E6%B5%B7%E6%BB%A9.jpg",
      articlelist: []
    };
  },
  async created() {
    this.articlelist = await this.getArticleList();
  },
  computed: {},
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
    EssayCard
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
  width: 100%;
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
