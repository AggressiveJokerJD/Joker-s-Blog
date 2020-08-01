<template>
  <div class="showbar">
    <transition name="fade">
      <span v-if="ifshow&!ifshowbar" class="icon showbar-icon" @click="ifshowbar=!ifshowbar">
        <ion-icon name="chevron-back-outline" size="large"></ion-icon>
      </span>
    </transition>
    <transition name="slide-fade">
      <span v-if="ifshowbar" class="icon showbar-icon" @click="ifshowbar=!ifshowbar">
        <ion-icon name="chevron-forward-outline" size="large"></ion-icon>
      </span>
    </transition>
    <transition name="slide-fade">
      <aside v-if="ifshowbar" class="showbar-contain">
        <div class="is-light is-bold">
          <ul class="menu">
            <li v-for="(menu,index) in menus" :key="index">
              <ion-icon :name="menu.icon"></ion-icon>
              <router-link class="showbar-link" :to="menu.link">{{menu.name}}</router-link>
            </li>
          </ul>
        </div>
      </aside>
    </transition>
  </div>
</template>

<script>
export default {
  name: "ShowBar",
  data() {
    return {
      ifshow: false,
      ifshowbar: false,
      whenseen: 150,
      menus: [
        { name: "Home", link: "/", icon: "home-outline" },
        { name: "Essay", link: "/essay", icon: "reader-outline" },
        { name: "Tags", link: "/tags", icon: "pricetag-outline" },
        { name: "TimeLine", link: "/timeline", icon: "shuffle-outline" },
        { name: "About", link: "/about", icon: "person-outline" }
      ]
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
      if (scrolltop > this.whenseen) {
        this.ifshow = true;
      } else {
        this.ifshow = false;
        this.ifshowbar = false;
      }
    }
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
.showbar-icon {
  position: fixed;
  bottom: 180px;
  right: 30px;
  width: 60px;
  height: 60px;
  cursor: pointer;
  background: #82e4d3;
  border-radius: 50%;
  z-index: 99;
}
.showbar-link {
  line-height: 30px;
  text-decoration: none;
  color: black;
}
.showbar-contain {
  position: fixed;
  bottom: 260px;
  right: 10px;
  width: 150px;
  cursor: pointer;
  background: #82e4d3;
  color: black;
  border-radius: 50%;
  z-index: 99;
}
ul {
  font-family: itc;
  list-style-type: disc;
  margin-block-start: 1em;
  margin-block-end: 1em;
  padding-inline-start: 50px;
  text-align: left;
  margin: 2px;
}
li:hover {
  opacity: 0.5;
}
.is-light {
  background-color: #82e4d3;
  color: black;
  padding: 10px 0px 10px 0px;
}
.is-bold {
  background-image: linear-gradient(
    141deg,
    #009e6c 0%,
    #00d1b2 71%,
    #00e7eb 100%
  );
}
ion-icon {
  color: #7a7e7d;
}
.fade-enter-active,
.fade-leave-active {
  transition: opacity 1s;
}
.fade-enter,
.fade-leave-to {
  opacity: 0;
}
.slide-fade-enter-active {
  transition: all 0.5s ease;
}
.slide-fade-leave-active {
  transition: all 0.3s cubic-bezier(1, 0.5, 0.8, 1);
}
.slide-fade-enter,
.slide-fade-leave-to {
  transform: translateX(10px);
  opacity: 0;
}
</style>
