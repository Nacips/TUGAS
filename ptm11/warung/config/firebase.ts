// Import the functions you need from the SDKs you need
import { initializeApp } from "firebase/app";
import { getFirestore } from "firebase/firestore";

// TODO: Add SDKs for Firebase products that you want to use
// https://firebase.google.com/docs/web/setup#available-libraries

// Your web app's Firebase configuration
// For Firebase JS SDK v7.20.0 and later, measurementId is optional
const firebaseConfig = {
  apiKey: "AIzaSyDdav6fy_2BuOZjBT6Uq12kcVlAnmztHlc",
  authDomain: "dbwarung-49cd7.firebaseapp.com",
  projectId: "dbwarung-49cd7",
  storageBucket: "dbwarung-49cd7.firebasestorage.app",
  messagingSenderId: "442146533072",
  appId: "1:442146533072:web:9fb5921d6b86e8de9378d5",
  measurementId: "G-T1C8V9LN97"
};

// Initialize Firebase
const app = initializeApp(firebaseConfig);
export const db = getFirestore(app);