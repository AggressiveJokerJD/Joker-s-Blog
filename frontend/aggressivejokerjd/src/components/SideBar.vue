<template>
  <aside id="sidebar" class="sidebar">
    <div class="is-light is-bold" :class="{affix: isaffix, affixtop: isaffixtop}">
      <div class="author">
        <img
          class="author-img"
          src="https://pic-bed-1259719447.cos.ap-chengdu.myqcloud.com/%E7%B4%A2%E7%BD%97.jpg"
        />
        <p class="author-name">{{name}}</p>
        <div class="author-des">{{des}}</div>
        <div class="author-link">
          <!--zhe li ke yi xuan ze v-for-->
          <span class="author-link-item">
            <a
              class="router-link"
              href="https://github.com/AggressiveJokerJD"
              target="_blank"
            >Github</a>
          </span>
          <span class="author-link-item">
            <a class="router-link" href="mailto:910368490@qq.com">E-Mail</a>
          </span>
        </div>
      </div>
    </div>
  </aside>
</template>

<script>
export default {
  name: "SideBar",
  data() {
    return {
      isaffix: false,
      isaffixtop: true,
      name: "Joker",
      des: "如果哲学可以填饱肚子，那人们都是哲学家了!"
    };
  },
  mounted() {
    window.addEventListener("scroll", this.affixTop);
    window.addEventListener("scroll", this.sideBar);
  },
  destroyed() {
    window.removeEventListener("scroll", this.affixTop);
    window.removeEventListener("scroll", this.sideBar);
  },
  methods: {
    affixTop() {
      var rollh =
        document.body.scrollTop === 0
          ? document.documentElement.scrollTop
          : document.body.scrollTop;
      var eletotop = document.getElementById("sidebar").offsetTop;
      if (rollh > eletotop - 15) {
        this.isaffixtop = false;
        this.isaffix = true;
      } else {
        this.isaffixtop = true;
        this.isaffix = false;
      }
    },
    sideBar() {
      var rollh =
        document.body.scrollTop === 0
          ? document.documentElement.scrollTop
          : document.body.scrollTop;
      var eleh = document.getElementById("sidebar").offsetHeight;
      var eletotop = document.getElementById("sidebar").offsetTop;
      var allrop = eleh + eletotop;
      if (rollh <= eletotop || rollh >= eletotop) {
        document.getElementById("sidebar").style.opacity = rollh / allrop;
      } else {
        document.getElementById("sidebar").style.opacity = 1;
      }
    }
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
.sidebar {
  width: 200px;
  padding-left: 10px;
  position: static;
  float: left;
  opacity: 0;
}
.is-light {
  background-color: #82e4d3;
  color: #fff;
  padding: 20px 10px 20px 10px;
}
.is-bold {
  background-image: linear-gradient(
    141deg,
    #009e6c 0%,
    #00d1b2 71%,
    #00e7eb 100%
  );
}
.sidebar.affixtop {
  width: 190px;
  margin-left: 10px;
}
.affix {
  width: 190px;
  position: fixed;
  top: 10px;
}
.author-img {
  border: 1px solid #eee;
  display: block;
  height: auto;
  margin: 0 auto;
  max-width: 140px;
  padding: 2px;
}
.author-name {
  text-align: center;
  color: white;
}
.author-des {
  color: black;
  opacity: 0.6;
  font-size: 0.8125em;
  margin-top: 0;
}
.author-link {
  display: flex;
  flex-wrap: wrap;
  margin-top: 10px;
  justify-content: center;
}
.author-link-item {
  width: 50%;
}
</style>
