<template>
  <div class="header">
    <div id="header-bar" class="header-bar">
      <nav class="navbar is-primary is-bold">
        <div class="container">
          <div class="navbar-brand">
            <a class="navbar-item" href="/">
              <img
                :src="url"
                style="width:250px !important;height:40px !important;max-height:initial;"
              />
            </a>
            <a
              role="button"
              class="navbar-burger burger"
              aria-label="menu"
              aria-expanded="false"
              data-target="headermenu"
            >
              <span aria-hidden="true"></span>
              <span aria-hidden="true"></span>
              <span aria-hidden="true"></span>
            </a>
          </div>

          <div id="headermenu" class="navbar-menu">
            <div class="navbar-end">
              <div v-for="(menu,index) in menus" :key="index" class="column is-pramary is-3">
                <router-link class="router-link" :to="menu.link">{{menu.name}}</router-link>
              </div>
            </div>
          </div>
        </div>
      </nav>
    </div>
  </div>
</template>

<script>
export default {
  name: "Header",
  data() {
    return {
      blogname: "AggressiveJokerJD's Blog",
      menus: [
        { name: "Home", link: "/" },
        { name: "Essay", link: "/essay" },
        { name: "Tags", link: "/tags" },
        { name: "TimeLine", link: "/timeline" },
        { name: "About", link: "/about" }
      ],
      url:
        "https://pic-bed-1259719447.cos.ap-chengdu.myqcloud.com/log-clear.png"
    };
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
</style>
