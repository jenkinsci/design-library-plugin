# design-library-plugin

<a href="https://weekly.ci.jenkins.io/design-library/">
  <picture>
     <source media="(prefers-color-scheme: dark)" srcset="https://raw.githubusercontent.com/jenkinsci/design-library-plugin/master/logo-dark.svg">
     <img src="https://raw.githubusercontent.com/jenkinsci/design-library-plugin/master/logo.svg">
  </picture>
</a>

[![Join the chat at https://gitter.im/jenkinsci/ux-sig](https://badges.gitter.im/jenkinsci/ux-sig.svg)](https://gitter.im/jenkinsci/ux-sig)
[![Jenkins Plugin](https://img.shields.io/jenkins/plugin/v/design-library.svg)](https://plugins.jenkins.io/design-library/)
[![Jenkins Plugin Installs](https://img.shields.io/jenkins/plugin/i/design-library.svg?color=blue)](https://plugins.jenkins.io/design-library/)
[![Crowdin](https://badges.crowdin.net/e/6332fe04e3c8d658f6df4f62cc70268b/localized.svg)](https://jenkins.crowdin.com/design-library-plugin)

Design Library makes it easy for developers to build complex and consistent interfaces using Jenkins UI components

This plugin contains:
- demonstration of UI components available in Jenkins
- snippets of Jelly ready-to-use examples

## Usage

You can open this project as a [Gitpod workspace](https://www.gitpod.io/) which comes pre-configured with all the tools you will need.

[![Open in Gitpod](https://gitpod.io/button/open-in-gitpod.svg)](https://gitpod.io/#https://github.com/jenkinsci/design-library-plugin)

If you prefer using IntelliJ IDEA, you can setup Gitpod integration with JetBrains Gateway using the instructions on [gitpod.io](https://www.gitpod.io/docs/ides-and-editors/intellij), 
which will open the workspace in IntelliJ IDEA using JetBrains Gateway.

Alternatively clone this project and run `mvn hpi:run`.

Go to the **Design Library** menu item or straight to http://localhost:8080/jenkins/design-library/ and play with UI components.

If you just want to see this plugin in action then you can also visit the latest version of this plugin in our [Jenkins "weekly" live installation](https://weekly.ci.jenkins.io/design-library/).


# UI 

The UI is ready to be consumed in a jenkins plugin and will be automatic created when you run `mvn install`. 
However, you can use different environments for development.

This webcomponent follows the [open-wc](https://github.com/open-wc/open-wc) recommendation.

Our components are developed with [lit](https://lit.dev/docs/)

## Prepare

Run `yarn` if you have not run `mvn install`.

## Development 

For rapid development, we recommend to use the local demo environment and 
as soon you want to test on the server run the `build`

### Local Demo with `parcel`

```bash
yarn start

Server running at http://localhost:1234
```

To run a local development server that serves the basic demo located in `index.html`

## Build for jenkins 
You can build the project with the `yarn build` command which will invoke `yarn css:build` and then will use `parcel` to package the build into one file `./src/main/webapp/js/index.ts` which contains all the necessary information for css and js (as well registering new web components).

This is the file your main `jelly` should import and normally a hard refresh on the jenkins plugin should update the view after you have invoked the build again.

```jelly
<script type="module" src="${resURL}/plugin/$PLUGIN~NAME/js/index.js" />
<link rel="stylesheet" href="${resURL}/plugin/$PLUGIN~NAME/js/index.css" type="text/css" />
```

### Create a new component

```bash
yarn :add newcomponent [ -t componentType || 'components' ]
```

This will create a new component (or the `componentType` you have chosen) and link it in the project hierarchy. 
If you do not define it we will fallback to `process.env.SWC_PREFIX` or `components`

For example if you want to create a new view you can do:

```bash
yarn :add myview -t views
```

### Styles

We use [tailwind](https://tailwindcss.com/docs) as underlying framework, you can either use it in your html/js or use it in the css file of the component.
We need the css file to exist since it will trigger the generation of the style definition file (it is heavily bootstrapped to only include the styles we need for the component). All components are using shadow DOM hence need to encapsulate their styles, since there is no leakage between the overall page and the component itself. e.g. you can have 2 classes with the same name defined but they are only in the scope of themselves not changing the other web component.

### husky integration

In case your project is in the top level you can activate husky support by adding the following to the script section of your `package.json`:

```package.json
"prepare": "husky install",
```
