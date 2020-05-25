<template>
    <div v-if="institute !== null">
        <p>{{institute.name}}</p>
        <p>{{institute.description}}</p>
        <p>{{institute.site}}</p>
        <p>{{institute.phone}}</p>
        <p>{{institute.mail}}</p>
    </div>
</template>
<script>
    export default {
        props: ["userId"],
        data() {
            return {
                instituteApi: this.$resource("/institute/get-institute-by-userId{/id}"),
                institute: null
            }
        },
        created() {
            this.getInstituteFromDb();
        },
        methods: {
            getInstituteFromDb() {
                this.instituteApi.get({id: this.userId}).then(res=> {
                    res.json().then(data => {
                        this.institute = data;
                    })
                })
            }
        }

    }
</script>
<style>

</style>