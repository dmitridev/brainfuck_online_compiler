<template>
  <v-app>
    <v-app-bar
      app
      color="primary"
      dark
    >
      <v-toolbar-title>Brainfuck compiler</v-toolbar-title>
      <v-spacer></v-spacer>
    </v-app-bar>

    <v-content>
      <BrainfuckTextField/>
    </v-content>
  </v-app>
</template>

<script>
import BrainfuckTextField from './components/BrainfuckTextField.vue'
export default {
  name: 'App',
  components: {
    BrainfuckTextField,
  },
  methods:{

  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
