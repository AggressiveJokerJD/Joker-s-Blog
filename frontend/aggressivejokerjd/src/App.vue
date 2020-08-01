<template>
  <div id="app">
    <Header></Header>
    <ToTop></ToTop>
    <router-view />
    <ShowBar></ShowBar>
  </div>
</template>

<script>
import Header from "@/components/Header.vue";
import ToTop from "@/components/ToTop.vue";
import ShowBar from "@/components/ShowBar.vue";
export default {
  name: "app",
  components: {
    Header,
    ToTop,
    ShowBar
  }
};
</script>

<style>
#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  height: auto;
  width: 100%;
}
/* .contain{
    background: #fff;
    padding: 40px;
    /* width: calc(100% - 200px);
    margin-left: 306px;
    width: 1355px;
} */
.router-link {
  height: 40px;
  line-height: 40px;
  text-decoration: none;
  color: #fff;
}
.router-link:hover {
  opacity: 0.5;
  border-bottom: 2px solid #fff;
}
</style>
<style lang="sass" scoped>
@import "~@/font/font.css"
</style>
