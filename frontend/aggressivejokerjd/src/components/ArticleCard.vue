<template>
  <div class="articlecard">
    <div class="title">
      <span class="article-date">{{days}}</span>
      <br />
      <span class="article-gmtdate">{{timestring}}</span>
      <router-link class="time-link" :to="`/article/`+info">
        <h4>{{title}}</h4>
      </router-link>
    </div>
    <div class="card-img">
      <figure class="image">
        <router-link class="time-link" :to="`/article/`+info">
          <img
            :src="`https://pic-bed-1259719447.cos.ap-chengdu.myqcloud.com/`+articleimg"
            class="responsive"
          />
        </router-link>
      </figure>
    </div>
    <div class="card-contain">
      <div class="des-info">{{des}}</div>
      <router-link v-for="(tag,index) in tags" :key="index" :to="`/tags/`+tag">
        <span class="tag">{{tag}}</span>
      </router-link>
    </div>
  </div>
</template>

<script>
export default {
  name: "ArticleCard",
  data() {
    return {
      info: this.title+","+ this.articleimg
    };
  },
  props: {
    title: String,
    articleimg: String,
    time: Date,
    des: String,
    tags: Array,
    id: Number
  },
  computed: {
    days: function() {
      var second = new Date() - this.time;
      return parseInt(second / (60 * 60 * 24 * 1000)) + 1;
    },
    timestring: function() {
      var str = this.time.toDateString();
      return str;
    }
  },
  methods: {
    // 使用store保存每篇文章的图片显示，已弃置
    setUrl() {
      this.$store.dispatch("actionSetUrl", this.articleimg);
    }
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
.articlecard {
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
  box-shadow: 0 0.5em 1em -0.125em rgba(10, 10, 10, 0.1),
    0 0 0 1px rgba(10, 10, 10, 0.02);
  color: #4a4a4a;
  max-width: 100%;
  position: relative;
}
.responsive {
  width: 100%;
  height: auto;
}
.card-img {
  transition: all 0.2s ease-in-out;
  width: 100%;
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
.article-date {
  font-weight: 500;
  color: #2c3e50ba;
  font-size: 1rem;
  font-family: itc;
}
.article-gmtdate {
  font-weight: 500;
  color: #2c3e50ba;
  font-size: 0.8rem;
  font-family: itc;
}
.article-date::after {
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
</style>
