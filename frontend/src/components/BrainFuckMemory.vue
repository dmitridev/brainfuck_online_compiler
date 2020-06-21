<template>
    <v-card>
        <hot-table settings="settings"
                   :data="characters"
                   licenseKey="non-commercial-and-evaluation" width="600" height="300">
        </hot-table>
    </v-card>
</template>

<script>
    import {HotTable} from '@handsontable/vue';
    import axios from "axios";
    export default {
        name: "BrainFuckMemory",
        components: {
            HotTable
        },
        data: () => ({
            colHeaders: true,
            characters:[],
            settings: {
                rowHeaders: true,
                colHeaders: true,
                licenseKey: 'non-commercial-and-evaluation'
            }
        }),
        methods: {
            onChange() {
                const val = document.getElementById("text-field").value;
                axios.post(
                    '/api/interpreter',
                    {
                        "code": val,
                        "params": ""
                    }, {
                        "Content-Type": "application/json"
                    }).then((response) => {
                    this.characters = response.data;
                }).catch((e) => {
                    console.log(e);
                });
            }

        }
    }
</script>

<style>
</style>