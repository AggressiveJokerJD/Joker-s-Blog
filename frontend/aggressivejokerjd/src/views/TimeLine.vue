<template>
  <div class="timeline">
    <div v-for="(section,index) in articletimeline" :key="index" class="timelinesection">
      <h1 class="timeline-year">{{section.sectiontime}}</h1>
      <div v-for="(contain,index) in section.sectioncontain" :key="index" class="sectioncontain">
        <router-link class="time-link" :to="`/article/`+contain.title+`,`+contain.url">{{contain.title}}</router-link>
        <br />
        <span class="article-gmtdate">{{getUTCString(contain.date)}}</span>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "TimeLine",
  data() {
    return {
      articletimeline: []
    };
  },
  async created() {
    await this.getTimeLineList();
  },
  methods: {
    getUTCString(date) {
      return date.toDateString();
    },
    async getTimeLineList() {
      await axios
        .post("http://localhost:8081/article/getTimeLine")
        .then((response) => {
          var list = response.data;
          for (let i = 0; i < list.length; i++) {
            var element = {
              sectiontime: list[i].sectiontime,
              sectioncontain: []
            };
            for (let j = 0; j < list[i].sectioncontain.length; j++) {
              var article = list[i].sectioncontain[j];
              element.sectioncontain.push({
                title: article.title,
                date: new Date(article.date),
                url: article.url
              });
            }
            this.articletimeline.push(element);
          }
        })
        .catch((error) => {
          console.log(error);
        });
    }
  }
};
</script>
<style scoped lang="scss">
.timeline {
  position: relative;
  max-width: 960px;
  margin: 0 auto;
  padding: 2em 1em;
  margin-top: 40px;
  margin-bottom: 40px;
}
.timeline::before {
  content: "";
  position: absolute;
  width: 6px;
  background-color: #82e4d3;
  top: 0;
  bottom: 0;
  left: 10px;
}
.timeline-year {
  color: #8586869d;
  padding: 8px;
  position: relative;
  background-color: inherit;
  font-size: 1.8em;
  text-align: left;
  margin-left: 30px;
  font-family: itc;
}
.timeline-year::before {
  content: "";
  position: absolute;
  width: 25px;
  height: 25px;
  right: -17px;
  background-color: rgb(99, 97, 97);
  border: 3px solid #1ddfb5;
  top: 15px;
  border-radius: 50%;
  z-index: 1;
  left: -45px;
}
.right::after {
  left: -16px;
}
.sectioncontain {
  font-family: itc;
  text-align: left;
  padding: 10px 10px;
  background-color: white;
  // position: relative;
  border-radius: 5px;
  margin-left: 40px;
  color: #aeb4b3af;
}
.article-gmtdate {
  font-weight: 500;
  color: #2c3e50ba;
  font-size: 0.8rem;
  font-family: itc;
}
.time-link {
  height: 40px;
  line-height: 40px;
  text-decoration: none;
  color: rgba(80, 78, 78, 0.685);
}
.time-link:hover {
  opacity: 0.5;
  border-bottom: 1px solid rgba(65, 231, 190, 0.637);
  color: #1ddfb5;
}
.time-link::before {
  content: "";
  position: absolute;
  width: 15px;
  height: 15px;
  right: -5px;
  background-color: rgb(99, 97, 97);
  border: 3px solid #1ddfb5;
  border-radius: 50%;
  z-index: 1;
  left: 5px;
  margin-top: 15px;
}
</style>
