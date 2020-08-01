import Vue from 'vue';
import VueRouter from 'vue-router';

Vue.use(VueRouter);

const routes = [
  {
    path: '/',
    name: 'Home',
    component: () => import('../views/Home.vue')
  },
  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  },
  {
    path: '/tags',
    name: 'Tags',
    component: () => import('../views/Tags.vue')
  },
  {
    path: '/timeline',
    name: 'TimeLine',
    component: () => import('../views/TimeLine.vue')
  },
  {
    path: '/essay',
    name: 'Essay',
    component: () => import('../views/Essay.vue')
  },
  {
    path: '/tags/:name',
    name: 'Tags',
    component: () => import('../views/Tags.vue'),
    props: true
  },
  {
    path: '/article/:info',
    name: 'article',
    component: () => import('../views/Article.vue'),
    props: true
  },
  {
    path: '/article',
    name: 'article',
    component: () => import('../views/Article.vue')
  }
];

const router = new VueRouter({
  routes,
  scrollBehavior(to, from, savedPosition) {
    return { x: 0, y: 0 };
  }
});

export default router;
