
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>TODO supply a title</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <!--<p>Something wrong, please click <a href="MainController?action=login">here</a> to return index page</p>-->
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>

<style>
@import url('https://fonts.googleapis.com/css?family=Nunito+Sans');
:root {
  --blue: #0e0620;
  --white: #fff;
  --green: #2ccf6d;
}
html,
body {
  height: 100%;
}
body {
  display: flex;
  align-items: center;
  justify-content: center;
  font-family:"Nunito Sans";
  color: var(--blue);
  font-size: 1em;
}
button {
  font-family:"Nunito Sans";
}
ul {
  list-style-type: none;
  padding-inline-start: 35px;
}
svg {
  width: 100%;
  visibility: hidden;
}
h1 {
  font-size: 7.5em;
  margin: 15px 0px;
  font-weight:bold;
}
h2 {
  font-weight:bold;
}
.hamburger-menu {
  position: absolute;
  top: 0;
  left: 0;
  padding: 35px;
  z-index: 2;

  & button {
    position: relative;
    width: 30px;
    height: 22px;
    border: none;
    background: none;
    padding: 0;
    cursor: pointer;

    & span {
      position: absolute;
      height: 3px;
      background: #000;
      width: 100%;
      left: 0px;
      top: 0px;
      transition: 0.1s ease-in;
      &:nth-child(2) {
        top: 9px;
      }
      &:nth-child(3) {
        top: 18px;
      }
    }
  }
  & [data-state="open"] {
    & span {
      &:first-child {
        transform: rotate(45deg);
        top: 10px;
      }
      &:nth-child(2) {
        width: 0%;
        opacity:0;
      }
      &:nth-child(3) {
        transform: rotate(-45deg);
        top: 10px;
      }
    }
  }
}
nav {
  position: absolute;
  height: 100%;
  top: 0;
  left: 0;
  background: var(--green);
  color: var(--blue);
  width: 300px;
  z-index: 1;
  padding-top: 80px;
  transform: translateX(-100%);
  transition: 0.24s cubic-bezier(.52,.01,.8,1);
  & li {
    transform: translateX(-5px);
    transition: 0.16s cubic-bezier(0.44, 0.09, 0.46, 0.84);
    opacity: 0;
  }
  & a {
    display: block;
    font-size: 1.75em;
    font-weight: bold;
    text-decoration: none;
    color: inherit;
    transition: 0.24s ease-in-out;
    &:hover {
      text-decoration: none;
      color: var(--white);
    }
  }
}
.btn {
  z-index: 1;
  overflow: hidden;
  background: transparent;
  position: relative;
  padding: 8px 50px;
  border-radius: 30px;
  cursor: pointer;
  font-size: 1em;
  letter-spacing: 2px;
  transition: 0.2s ease;
  font-weight: bold;
  margin: 5px 0px;
  &.green {
    border: 4px solid var(--green);
    color: var(--blue);
    &:before {
      content: "";
      position: absolute;
      left: 0;
      top: 0;
      width: 0%;
      height: 100%;
      background: var(--green);
      z-index: -1;
      transition: 0.2s ease;
    }
    &:hover {
      color: var(--white);
      background: var(--green);
      transition: 0.2s ease;
      &:before {
        width: 100%;
      }
    }
  }
}
@media screen and (max-width:768px) {
  body {
    display:block;
  }
  .container {
    margin-top:70px;
    margin-bottom:70px;
  }
} 
</style>
    <body style="background-image: url('images/404error.jpg');
                 background-size: 100%;
                 background-position-y: 60%;
                 color: white;">
        
<main>
    <div class="container">
    <div class="row">
        
      <div class="col-md-6 align-self-center">
          <br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
        <h1>404</h1>
        <h2>UH OH! You're lost.</h2>
        <p>The page you are looking for does not exist.
          How you got here is a mystery. But you can click the button below
          to go back to the homepage.
        </p>
        <form action="MainController">
            <button type="submit" class="btn green" style="color: #3f77df; border-color: white; background-color: white" name="action" value="Home">HOME</button>
        </form>
      </div>
    </div>
  </div>
</main>
    </body>

</html>
