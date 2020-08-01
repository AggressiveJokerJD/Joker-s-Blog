<template>
  <div class="essaycard">
    <div class="essaycard-head">
      <div class="title">
        <span class="essay-date">{{days}}</span>
        <br />
        <span class="essay-gmtdate">{{timestring}}</span>
        <h4>{{title}}</h4>
      </div>
      <div class="card-img">
        <figure class="image">
          <img
            class="responsive"
            :src="`https://pic-bed-1259719447.cos.ap-chengdu.myqcloud.com/`+articleimg"
          />
        </figure>
      </div>
      <div class="card-contain">
        <div class="des-info">{{des}}</div>
      </div>
    </div>
    <div class="essaycard-contain">
      <mavon-editor
      class="essay"
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
  </div>
</template>

<script>
import { mavonEditor } from "mavon-editor";
// import axios from "axios";
export default {
  name: "EssayCard",
  data() {
    return {
      articleContent: ""
    };
  },
  components: {
    mavonEditor
  },
  props: {
    title: String,
    articleimg: String,
    time: Date,
    des: String,
    tags: Array,
    id: Number
  },
  async created() {
    // this.articleContent = await this.getArticle(this.title);
  },
  computed: {
    days: function () {
      var second = new Date() - this.time;
      return parseInt(second / (60 * 60 * 24 * 1000)) + 1;
    },
    timestring: function () {
      var str = this.time.toDateString();
      return str;
    }
  },
  methods: {
    // 使用store保存每篇文章的图片显示，已弃置
    setUrl() {
      this.$store.dispatch("actionSetUrl", this.articleimg);
    }
    // async getArticle(name) {
    //   var content = "";
    //   await axios
    //     .post("http://localhost:8081/article/getContainer", {
    //       objectKey: name
    //     })
    //     .then(response => {
    //       content = response.data;
    //     })
    //     .catch(error => {
    //       console.log(error);
    //     });
    //   return content;
    // }
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
.essaycard {
  position: relative;
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
  box-shadow: 0 0.5em 1em -0.125em rgba(10, 10, 10, 0.1),
    0 0 0 1px rgba(10, 10, 10, 0.02);
  color: #4a4a4a;
  position: relative;
}
.essaycard{
  display: flex;
  justify-content: space-around;
  height: auto;
}
.essaycard-head{
  max-width: 33.3%;
  height: 80vh;
}
.essaycard-contain{
  max-width: 66.6%;
  height: 80vh;
}
.essay{
  height: 80%;
  margin-top: 80px;
}
.responsive {
  height: 50%;
}
.card-img {
  transition: all 0.2s ease-in-out;
  width: auto;
  border-radius: calc(0.25rem - 1px);
  vertical-align: middle;
  border-style: none;
}
.title {
  width: 100%;
  padding: 0.75rem 10%;
  margin-bottom: 0;
  border-bottom: 0 solid rgba(0, 0, 0, 0.125);
  text-align: left;
}
.essay-date {
  font-weight: 500;
  color: #2c3e50ba;
  font-size: 1rem;
  font-family: itc;
}
.essay-gmtdate {
  font-weight: 500;
  color: #2c3e50ba;
  font-size: 0.8rem;
  font-family: itc;
}
.essay-date::after {
  content: " DAYS AGO";
}
h4 {
  color: #838383;
  font-size: 1.5rem;
  font-weight: 300;
  line-height: 2;
}
h4:hover {
  color: #42b983;
  opacity: 0.7;
}
.card-img:hover {
  box-shadow: 0 18px 32px -18px #000;
  transform: translateY(-6px);
}
.card-contain {
  text-align: right;
  margin-top: 4px;
  padding: 0 10%;
}
.des-info {
  text-align: left;
  letter-spacing: 0.5px;
  padding: 10px 4%;
  font-size: 0.9rem;
}
.des-info::after {
  font-size: 1.5rem;
  content: "...";
  font-weight: 300;
}
.des-info::first-letter {
  color: #42b983;
  font-size: 1.3rem;
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
  font-size: 1.1rem;
  font-family: itc;
}
.essaycard-contain{
  width: 60%;
  height: 100vh;
}
img{
  height: 50%;
}
</style>
