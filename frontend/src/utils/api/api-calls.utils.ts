import axios from "axios";
import { API_BASE_URL } from "./api-routes.util";

const apiDetails = {
  baseURL: API_BASE_URL,
  headers: {
    'Content-Type': 'application/json'
  }
};

export interface ApiVariables {
  accessToken: string;
}

export const makePostRequest = async (url: string, data: any) => {
  const clientApi = axios.create({...apiDetails});
  return await clientApi.post(url, data);
};

export const makeGetRequest = async (url: string, variables: ApiVariables) => {
  const clientApi = axios.create({
    ...apiDetails,
    headers: {
      ...apiDetails.headers,
      Authorization: `Bearer ${variables.accessToken}`
    }
  });

  return await clientApi.get(url);
}
