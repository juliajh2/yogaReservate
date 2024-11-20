<template>

    <v-data-table
        :headers="headers"
        :items="searchClass"
        :items-per-page="5"
        class="elevation-1"
    ></v-data-table>

</template>

<script>
    const axios = require('axios').default;

    export default {
        name: 'SearchClassView',
        props: {
            value: Object,
            editMode: Boolean,
            isNew: Boolean
        },
        data: () => ({
            headers: [
                { text: "id", value: "id" },
            ],
            searchClass : [],
        }),
          async created() {
            var temp = await axios.get(axios.fixUrl('/searchClasses'))

            temp.data._embedded.searchClasses.map(obj => obj.id=obj._links.self.href.split("/")[obj._links.self.href.split("/").length - 1])

            this.searchClass = temp.data._embedded.searchClasses;
        },
        methods: {
        }
    }
</script>

