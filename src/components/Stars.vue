<template>
  <div class="stars">
    <span class="text-left">{{leftText}}</span>
    <span class="star" v-for="i in stars">★</span> <span class="star-grey" v-for="i in 5-stars">★</span>
    <span class="text-right">{{rightText}}</span>
  </div>
</template>

<script>
export default {
  name: "Stars",
  props: {
    stars: {
      type: Number,
      default: 3
    },
    leftText: {
      type: String,
      default: ''
    },
    rightText: {
      type: String,
      default: ''
    },
    starSize: {
      type: String,
      default: "200%"
    },
    fontSize: {
      type: Number,
      default: "25px"
    }
  }
};
</script>

<style scoped>

.stars{
  display: flex;
  align-items: center;
}
.star{
  font-size: v-bind(starSize);
  color: gold;
  padding-top: 5px;
}
.star-grey{
  font-size: v-bind(starSize);
  color: lightgray;
  padding-top: 5px;
}
.text-left {
  text-align: right;
  margin-right: 10px;
  font-weight: bold;
  font-size: v-bind(fontSize);
  margin-top: 12px;
}
.text-right {
  text-align: left;
  margin-left: 15px;
  font-weight: bold;
  font-size: v-bind(fontSize);
  margin-top: 12px;
}
</style>
