<template>
  <div class="about">
    <HeaderContain
      :url="`URL(`+url+`)`"
      :h1message="h1message"
      :h2message="h2message"
      :h3message="h3message"
    ></HeaderContain>
  </div>
</template>

<script>
// @ is an alias to /src
import HeaderContain from "../components/HeaderContain.vue";
export default {
  name: "About",
  data() {
    return {
      h1message:
        "<p>AggressiveJokerJD:</p><p>&nbsp&nbspAggressive:The first difficult word</p> <p>&nbsp&nbsp&nbsp&nbsp&nbsp&nbspI keep in mind.</p><p>&nbsp&nbspJoker: One of my favorite characters in</p><p>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp'One Piece'.</p><p>&nbsp&nbspJD: My name.",
      h2message:
        "<p>想要弄一个好看一点的博客，而且可以自己控制。</p><p>弄了很多的花里胡哨，但好看一点真可以让自己有继续写下去的动力。</p><p>希望真的可以有一些提升，而不是仅此而已。</p>",
      h3message: "班夫国家公园赫伯特湖",
      url:
        "https://aggressivejoker-image-1259719447.cos.ap-chengdu.myqcloud.com/%E7%8F%AD%E8%8A%99%E5%9B%BD%E5%AE%B6%E5%85%AC%E5%9B%AD%E4%B8%AD%E7%9A%84%E8%B5%AB%E4%BC%AF%E7%89%B9%E6%B9%96.jpg"
    };
  },
  components: {
    HeaderContain
  }
};
</script>
<style lang="scss" scoped>
</style>
