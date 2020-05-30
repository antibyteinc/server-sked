<template>
    <div class="wrapper">
        <header-page :user="user"></header-page>
        <router-view></router-view>
        <footer-page></footer-page>
    </div>
</template>
<script>
    import HeaderPage from "../components/header/HeaderPage.vue";
    import FooterPage from "../components/header/FooterPage.vue";
    import MainPage from "./Main.vue";

    export default {
        data() {
            return {
                user: frontendData.username
            }
        },
        components: {
            HeaderPage, MainPage, FooterPage
        }
    }
</script>
<style>
    :root {
        /*fonts*/
        --fontLato: 'Lato', sans-serif;
        --fontHand: 'Gochi Hand', cursive;
        /*colors*/
        --mainDark: #24292e;
        --mainWhite: #f4f4f4;
        --mainSilver: #7d848b;
        /*transitions*/
        --transDef: all .3s ease;
    }
    * {
        -webkit-box-sizing: border-box;
        box-sizing: border-box;
        line-height: 1;
        margin: 0;
        padding: 0;
    }

    h1, h2, h3, h4, h5, h6 {
        margin: 0;
        padding: 0;
    }

    a,
    a:hover,
    a:visited,
    a:active,
    a:focus {
        text-decoration: none;
    }

    .container {
        margin: 0 auto;
        width: 100%;
        max-width: 1200px;
        padding: 0 15px;
    }

    body {
        font-family: var(--fontLato);
        font-size: 16px;
        font-weight: 400;
        line-height: 1;
        min-width: 320px;
        position: relative;
        overflow-x: hidden;
    }
    .button {
        padding: 10px 15px;
        border: 2px solid var(--mainWhite);
        color: var(--mainWhite);
        transition: var(--transDef);
    }
    .button:hover {
        background-color: var(--mainWhite);
        color: var(--mainDark);
    }
    .button:active {
        transform: scale(0.95);
    }

    .button_constructor {
        padding: 10px 15px;
        border: 2px solid var(--mainDark);
        color: var(--mainDark);
        transition: var(--transDef);
    }
    .button_constructor:hover {
        background-color: var(--mainDark);
        color: var(--mainWhite);
    }
    .button_constructor:active {
        transform: scale(0.95);
    }
    /* --- sections --- */
    .title {
        font-family: var(--fontHand);
        margin-bottom: 30px;
        color: white;
    }

    .title_constructor {
        font-family: var(--fontHand);
        margin-bottom: 30px;
        color: black;
    }


    .section {
        width: 100%;
        padding: 60px 0;
        border-bottom: 1px solid var(--mainDark);
    }
    .start-section {
        min-height: 100vh;
        background-position: center;
        background-size: cover;
        background-repeat: no-repeat;
        position: relative;
    }
    .section__inner {
        display: flex;
        flex-direction: row;
        width: 100%;
        min-height: 80vh;
        flex-wrap: wrap;
        justify-content: center;
    }
    .section__inner .left,
    .section__inner .right {
        width: 50%;
        padding: 0 15px;
        margin-top: 5vh;
    }

    .right {

    }
    .image {
        display: block;
        margin: 0 auto;
        width: 100%;
    }
    .image.phone {
        width: 300px;
    }

    /* --- start section ---*/
    .start-section .start-search {
        max-width: 500px;
        width: 100%;
        border: 1px solid var(--mainDark);
        border-radius: 20px;
        padding: 5px 10px;
        display: flex;
        flex-direction: row;
        justify-content: space-between;
    }
    .start-section__inner {
        width: 100%;
        max-width: 500px;
        position: absolute;
        transform: translate(-50%, -50%);
        top: 30%;
        left: 50%;
    }
    .search-input {
        width: calc(100% - 20px);
        border: none;
        outline: none;
        background-color: transparent;
    }
    .search-btn {
        border: none;
        outline: none;
        background-color: transparent;
        cursor: pointer;
        color: var(--mainDark);
    }
    .title.start {
        text-align: center;
    }

    .constructor {
        margin-left: 50vh;

    }

</style>