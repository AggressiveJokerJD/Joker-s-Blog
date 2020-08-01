<template>
  <div>
    <HeaderContain class="articleContain" :url="`url(https://pic-bed-1259719447.cos.ap-chengdu.myqcloud.com/`+url+`)`" :h1message="`<br/>&nbsp&nbsp&nbsp&nbsp&nbsp`+title+`&nbsp&nbsp&nbsp&nbsp`"></HeaderContain>
    <mavon-editor
      class="article"
      ref="mavon"
      defaultOpen="preview"
      v-model="articleContent"
      :navigation="false"
      :toolbarsFlag="false"
      :subfield="false"
      :preview="true"
      :transition="true"
      codeStyle="github"
      style="position:initial;"
    />
  </div>
</template>

<script>
import { mavonEditor } from "mavon-editor";
import HeaderContain from "@/components/HeaderContain.vue";
import axios from "axios";
export default {
  name: "article",
  props: {
    info: String
  },
  components: {
    mavonEditor,
    HeaderContain
  },
  data() {
    return {
      articleContent: "",
      title: "",
      url: ""
    };
  },
  async created() {
    // this.url = this.$store.getters.getUrl;
    this.splitInfo();
    this.articleContent = await this.getArticle(this.title);
  },
  methods: {
    async getArticle(name) {
      var content = "";
      await axios
        .post("http://localhost:8081/article/getContainer", {
          objectKey: name
        })
        .then(response => {
          content = response.data;
        })
        .catch(error => {
          console.log(error);
        });
      return content;
    },
    splitInfo() {
      var infos = this.info.split(",");
      this.title = infos[0];
      this.url = infos[1];
    }
  }
};
</script>
<style scoped lang="scss">
.articleContain{
  max-width: 980px;
  min-width: 980px;
  margin: 0 auto;
}
.article {
  padding: 2em 1em;
  margin: 0 auto;
  height: 100%;
  max-width: 980px;
}
.article ol ul{
  list-style-type:decimal;
  list-style-position:inside;
}
</style>
