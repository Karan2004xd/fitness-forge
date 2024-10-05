import { initializeApp } from "firebase/app";

import {
  getAuth,
  GoogleAuthProvider,
  signInWithPopup,
} from 'firebase/auth';

const apiKey = process.env.REACT_APP_FIREBASE_API_KEY;

const firebaseConfig = {
  apiKey: apiKey,
  authDomain: "fitnessforge-6b185.firebaseapp.com",
  projectId: "fitnessforge-6b185",
  storageBucket: "fitnessforge-6b185.appspot.com",
  messagingSenderId: "658083258493",
  appId: "1:658083258493:web:f49928bc16db712cdcd114",
  measurementId: "G-JSJSRXJNS0"
};

// Initialize Firebase
initializeApp(firebaseConfig);

const provider = new GoogleAuthProvider();
provider.setCustomParameters({
  prompt: 'select_account'
});

export const auth = getAuth();
export const signInWithGooglePopUp = () => signInWithPopup(auth, provider);
