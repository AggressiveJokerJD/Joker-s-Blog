<template>
  <section id="intro" style="opacity: 1;">
    <div class="headercontain hero">
      <h1>
        <p v-html="h1message"></p>
      </h1>
      <h2>
        <p v-html="h2message"></p>
      </h2>
      <h3>
        <p>{{h3message}}</p>
      </h3>
      <div class="slice left" :style="{backgroundImage: url}"></div>
      <div class="slice right" :style="{backgroundImage: url}"></div>
    </div>
  </section>
</template>

<script>
export default {
  name: "HeaderContain",
  props: {
    h1message: String,
    h2message: String,
    h3message: String,
    url: String
  },
  data() {
    return {
    };
  },
  mounted() {
    window.addEventListener("scroll", this.headerContain);
  },
  methods: {
    headerContain() {
      var rollh =
        document.body.scrollTop === 0
          ? document.documentElement.scrollTop
          : document.body.scrollTop;
      var eleh = document.getElementById("intro").offsetHeight;
      var eletotop = document.getElementById("intro").offsetTop;
      var allrop = eleh + eletotop;
      if (rollh <= eletotop || rollh >= eletotop) {
        document.getElementById("intro").style.opacity = 1 - rollh / allrop;
      } else {
        document.getElementById("intro").style.opacity = 1;
      }
    }
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
.headercontain {
  display: grid;
  grid-template-columns: minmax(20%, 350px) 58px minmax(max-content, 1fr) 75px minmax(
      20%,
      350px
    );
  grid-template-rows: 220px 30px minmax(max-content, 1fr) 75px 275px;
  grid-template-areas:
    ". . . . rs"
    "ls . . . rs"
    "ls . h1 . rs"
    "ls . h2 . rs"
    "ls . . . h3";
}
#intro {
  display: flex;
  justify-content: center;
  min-height: 100vh;
  width: 210vh;
  background-image: linear-gradient(141deg, #009e6c 0%, #00d1b2 71%, #00e7eb 100%);
}
#intro h1 {
  font-family: itc;
  z-index: 1;
  opacity: 0.9;
  grid-area: h1;
  font-size: 35px;
  color: #fff;
  text-align: left;
}
#intro h2 {
  z-index: 1;
  opacity: 0.5;
  grid-area: h2;
  font-size: 20px;
  color: black;
  text-align: left;
}
#intro h3 {
  z-index: 1;
  opacity: 0.3;
  grid-area: h3;
  font-size: 15px;
  color: black;
  text-align: right;
}
.hero.is-fullheight {
  min-height: 100vh;
}
.slice {
  width: 100%;
  height: 100%;
  background-size: cover;
  box-shadow: 0 20px 90px 0 rgba(0, 0, 0, 0.08), 0 9px 30px 0 rgba(0, 0, 0, 0.1);
}
.slice.left {
  grid-area: ls;
  background-position: left;
}
.slice.right {
  grid-area: rs;
  background-position: right;
}
</style>
