import axios, { AxiosResponse } from "axios";
import { API_BASE_URL } from "./api-routes.util";

const apiDetails = {
  baseURL: API_BASE_URL,
  headers: {
    'Content-Type': 'application/json'
  }
};

export enum REQUEST_TYPE {
  GET, POST, PUT, DEL
};

export interface ApiVariables {
  accessToken?: string;
  type: REQUEST_TYPE,
  data?: any
}

export const makeRequest = async (url: string, variables: ApiVariables) => {
  const { data, type, accessToken } = variables;

  let clientApi;
  if (variables.accessToken) {
    clientApi = axios.create({
      ...apiDetails,
      headers: {
        ...apiDetails.headers,
        Authorization: `Bearer ${accessToken}`
      }
    });
  } else {
    clientApi = axios.create({
      ...apiDetails,
    })
  }

  let response: AxiosResponse;

  switch (type) {
    case REQUEST_TYPE.GET:
      response = await clientApi.get(url);
      break;
    case REQUEST_TYPE.PUT:
      response = await clientApi.put(url, data);
      break;
    case REQUEST_TYPE.DEL:
      response = await clientApi.delete(url, data);
      break;
    case REQUEST_TYPE.POST:
      response = await clientApi.post(url, data);
      break;
    default:
      const error = {
        response: {
          data: {
            message: "Invalid Request"
          }
        }
      }
      throw error;
  }
  return response;
};

export const getRequestParamsUrl = (filters: Map<string, string[] | string | number>, url: string) => {
  const queryParams: string[] = [];

  filters.forEach((value, key) => {
    if (value !== undefined && value !== null) {
      if (typeof value === 'string' || typeof value === 'number') {
        queryParams.push(`${key}=${encodeURIComponent(value.toString())}`);
      } else if (Array.isArray(value)) {
        queryParams.push(`${key}=${value.map(v => encodeURIComponent(v)).join(',')}`);
      }
    }
  });

  if (queryParams.length > 0) {
    url += `${queryParams.join('&')}`;
  }

  return url;
};
