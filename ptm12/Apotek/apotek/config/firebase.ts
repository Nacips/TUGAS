// Import the functions you need from the SDKs you need

import { initializeApp } from "firebase/app";
import { getFirestore } from "firebase/firestore";

// TODO: Add SDKs for Firebase products that you want to use
// https://firebase.google.com/docs/web/setup#available-libraries

// Your web app's Firebase configuration
// For Firebase JS SDK v7.20.0 and later, measurementId is optional
const firebaseConfig = {
  apiKey: "AIzaSyCpiRabBzMfZbvTq77qWXEFPN-wiC2a-ZA",
  authDomain: "dbobat-47bbe.firebaseapp.com",
  projectId: "dbobat-47bbe",
  storageBucket: "dbobat-47bbe.firebasestorage.app",
  messagingSenderId: "1049451377609",
  appId: "1:1049451377609:web:dcb79fe0b1abb5816bee18",
  measurementId: "G-J19JCRXBTZ"
};

// Initialize Firebase
const app = initializeApp(firebaseConfig);
export const db = getFirestore(app);