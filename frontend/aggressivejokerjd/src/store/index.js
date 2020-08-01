import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);

const store = new Vuex.Store({
  state: {
    url: ""
  },
  getters: {
    getUrl(state) {
      return state.url;
    }
  },
  mutations: {
    setUrl(state, url) {
      state.url = url;
    }
  },
  actions: {
    actionSetUrl(context, img) {
      context.commit('setUrl', img);
    }
  },
  modules: {
  }
});

export default store;

// const article = {
//   state: {
//     url: ""
//   },
//   getters: {
//     getUrl(state) {
//       return state.url;
//     }
//   },
//   mutations: {
//     setUrl(state, url) {
//       state.url = url;
//     }
//   },
//   actions: {
//     actionSetUrl(context, img) {
//       context.commit('setUrl', img);
//     }
//   }
// };

// const store = new Vuex.Store({
//   modules: {
//     articleModel: article
//   },
//   plugins: [
//     createVuexAlong({
//       name: "blog-vuex-along",
//       local: {
//         list: ["articleModel"],
//         isFilter: true
//       },
//       session: {
//         list: ["articleModel.url"]
//       }
//     })
//   ]
// });
