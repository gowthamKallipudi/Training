import { legacy_createStore as createStore } from "redux";

const initialState = {
  userName: "",
  emailId: "",
  phoneNumber: "",
  password: "",
};

const autentication = (state = initialState, action) => {
  if (action.type === "Login") {
    return action.payload;
  } else if (action.type === "Logout") {
    return initialState;
  } else {
    return initialState;
  }
};

const auth = createStore(autentication);
export { auth };
