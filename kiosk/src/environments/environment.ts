// This file can be replaced during build by using the `fileReplacements` array.
// `ng build` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

export const environment = {
  production: false,
  api_url:"http://192.99.108.204:3001/api",
  firebaseConfig: {
    apiKey: "AIzaSyAkFfKNunad06otYta2ReeDKve4s2JBa5Y",
    authDomain: "fifo-39066.firebaseapp.com",
    projectId: "fifo-39066",
    storageBucket: "fifo-39066.appspot.com",
    messagingSenderId: "1031591606441",
    appId: "1:1031591606441:web:87d467f7c9901f4deb11b8",
    measurementId: "${config.measurementId}"
  }
};

/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
// import 'zone.js/plugins/zone-error';  // Included with Angular CLI.
