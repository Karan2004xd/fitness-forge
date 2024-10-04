export const API_BASE_URL = "http://localhost:8080/";

export enum AUTHENTICATION {
  authencticateMember = 'auth'
};

export enum MEMBER_API_ROUTES {
  createMember = 'member/create',
  getMember = 'member/',
  getRefreshToken = 'member/auth_refresh/'
};
