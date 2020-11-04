<template>
  <v-app>
    <v-app-bar app color="primary" dark>
      <v-toolbar-title>Brainfuck compiler</v-toolbar-title>
    </v-app-bar>
    <v-content>
      <MonacoEditor theme="vs-light" language="javascript" @change="onChange" />
    </v-content>
    <v-footer>
      <v-dialog
        fullscreen
        transition="dialog-bottom-transition"
        v-model="dialog"
      >
        <v-card>
          <v-toolbar dark color="primary">
            <v-btn icon dark @click="dialog = false">
              <v-icon>mdi-close</v-icon>
            </v-btn>
            <v-toolbar-title>Результат</v-toolbar-title>
          </v-toolbar>

          <v-card-text>{{ result }}</v-card-text>
        </v-card>
      </v-dialog>
      <v-btn text @click="run(text)"> Запустить </v-btn>
      <v-spacer/>
      <v-btn text @click="dialog=true">{{result ? "Результат: " + result:""}}</v-btn>
    </v-footer>
  </v-app>
</template>

<script>
import MonacoEditor from "monaco-editor-vue";
import axios from "axios";
export default {
  name: "App",
  components: {
    MonacoEditor,
  },
  data: () => ({
    text: "",
    result: "",
    dialog: false,
  }),
  methods: {
    onChange(v) {
      this.text = v;
    },
    async run(text) {
      this.dialog = true;
      const { data } = await axios.post("/api/interpreter/", {
        code: text,
        params: "",
      });
      this.result = data;
    },
  },
};
</script>
