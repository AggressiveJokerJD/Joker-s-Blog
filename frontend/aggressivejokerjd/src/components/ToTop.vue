<template>
  <div class="totop">
    <transition name="fade">
      <span v-if="totopflag" class="icon totop-icon">
        <ion-icon name="chevron-up-outline" size="large" @click="backTop"></ion-icon>
      </span>
    </transition>
  </div>
</template>

<script>
export default {
  name: "ToTop",
  data() {
    return {
      totopflag: false,
      whenseen: 150
    };
  },
  mounted() {
    window.addEventListener("scroll", this.scrollToTop);
  },
  destroyed() {
    window.removeEventListener("scroll", this.scrollToTop);
  },
  methods: {
    scrollToTop() {
      var scrolltop =
        document.documentElement.scrollTop || document.body.scrollTop;
      scrolltop > this.whenseen
        ? (this.totopflag = true)
        : (this.totopflag = false);
    },
    backTop() {
      var top = document.documentElement.scrollTop || document.body.scrollTop;
      var speed = Math.floor(top / 10);
      const timeTop = setInterval(() => {
        document.body.scrollTop = document.documentElement.scrollTop = top -= speed;
        if (top <= 0) {
          clearInterval(timeTop);
        }
      }, 20);
    }
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
.totop-icon {
  position: fixed;
  bottom: 100px;
  right: 30px;
  width: 60px;
  height: 60px;
  cursor: pointer;
  background: #82e4d3;
  border-radius: 50%;
  z-index: 99;
}
.fade-enter-active,
.fade-leave-active {
  transition: opacity 1s;
}
.fade-enter,
.fade-leave-to {
  opacity: 0;
}
</style>
