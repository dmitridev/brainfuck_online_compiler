<template>
    <v-container>
        <v-card class="mx-auto pa-2 my-0">

            <v-textarea id="text-field" label="Введите код" class="ma-10">
                >
            </v-textarea>
            <v-divider class="my-4"></v-divider>
            <v-btn @click="onChange()" class="mx-2" color="primary">Выполнить

            </v-btn>
            <v-divider class="my-4"></v-divider>
            <v-toolbar-title>Ячейки</v-toolbar-title>

            <v-data-table :hide-default-footer="true" :disable-pagination="true"
                          :headers="headers" :items="characters" class="elevation-1 ma-10" id="text-result"
                          label="Result"
                          auto-grow>
                <template v-slot:items="props">
                    <td>{{props.c0}}</td>
                    <td>{{props.c1}}</td>
                    <td>{{props.c2}}</td>
                    <td>{{props.c3}}</td>
                    <td>{{props.c4}}</td>
                    <td>{{props.c5}}</td>
                    <td>{{props.c6}}</td>
                    <td>{{props.c7}}</td>
                    <td>{{props.c8}}</td>
                    <td>{{props.c9}}</td>
                </template>
                <template slot="no-data">
                    нажмите на кнопку для результата
                </template>
            </v-data-table>
        </v-card>
    </v-container>
</template>

<script>
    import axios from 'axios';

    export default {
        name: 'BrainfuckTextField',
        data: () => ({
            characters: [],
            headers: [
                {text: "r1", value: "r0.ch"},
                {text: "r2", value: "r1.ch"},
                {text: "r3", value: "r2.ch"},
                {text: "r4", value: "r3.ch"},
                {text: "r5", value: "r4.ch"},
                {text: "r6", value: "r5.ch"},
                {text: "r7", value: "r6.ch"},
                {text: "r8", value: "r7.ch"},
                {text: "r9", value: "r8.ch"},
                {text: "r10", value: "r9.ch"},
            ]

        }),
        methods: {
            async onChange() {
                const val = document.getElementById("text-field").value;
                if (val === "") alert("Введите данные в строку кода");
                const result = await axios.post(
                    '/api/interpreter',
                    {
                        "code": val,
                        "params": ""
                    }, {
                        "Content-Type": "application/json"
                    });
                this.characters = result.data;
                console.log(this.characters);
            }

        }

    }


</script>
