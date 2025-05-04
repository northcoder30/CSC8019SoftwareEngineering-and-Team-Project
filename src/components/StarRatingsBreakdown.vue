<template>
  <div class="rating-breakdown">
    <div v-for="star in [5, 4, 3, 2, 1]" :key="star" class="breakdown-line">
      <div class="breakdown-stars">
        <template v-for="i in star">
          <svg
              viewBox="0 0 24 24"
              class="star filled"
              xmlns="http://www.w3.org/2000/svg"
          >
            <path
                d="M12 .587l3.668 7.571L24 9.75l-6 5.847L19.335 24 12 20.01 4.665 24 6 15.597 0 9.75l8.332-1.592z"
            />
          </svg>
        </template>
      </div>
      <span class="votes">{{ ratingCounts[star] || 0 }} votes</span>
    </div>
  </div>
</template>

<script>
export default {
  name: "RatingBreakdown",
  props: {
    ratingCounts: {
      type: Object,
      required: true,
    },
  },
};
</script>

<style scoped>
.rating-breakdown {
  margin-top: 20px;
}
.breakdown-line {
  display: flex;
  align-items: center;
  margin-bottom: 8px;
}
.breakdown-stars {
  display: flex;
  margin-right: 10px;
}
.filled path {
  fill: gold;
}
.votes {
  color: #333;
}
</style>
