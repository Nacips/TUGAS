// Import the functions you need from the SDKs you need
import { initializeApp } from "firebase/app";
import { getAuth } from "firebase/auth";
// TODO: Add SDKs for Firebase products that you want to use
// https://firebase.google.com/docs/web/setup#available-libraries

// Your web app's Firebase configuration
// For Firebase JS SDK v7.20.0 and later, measurementId is optional
const firebaseConfig = {
  apiKey: "AIzaSyDE4n5GfvtPHQwcFw1xsiLj4bj1HsKzMOg",
  authDomain: "crudbioskop-36125.firebaseapp.com",
  projectId: "crudbioskop-36125",
  storageBucket: "crudbioskop-36125.firebasestorage.app",
  messagingSenderId: "1054432225534",
  appId: "1:1054432225534:web:4967c4b8837fecdb7b9daf",
  measurementId: "G-9HW3Z86JSP"
};

// Initialize Firebase
const app = initializeApp(firebaseConfig);
export const auth = getAuth(app);