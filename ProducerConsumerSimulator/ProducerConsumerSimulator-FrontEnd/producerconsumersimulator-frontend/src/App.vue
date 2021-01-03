<template>
  <div id="app">
    <Shapes />
  </div>
</template>

<script>
import Shapes from "./components/Shapes.vue";

export default {
  name: "App",
  components: {
    Shapes,
  },
};
</script>

<style>
* {
  background-color: aquamarine;
}
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
