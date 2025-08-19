import axios from 'axios'

export const apiClient = axios.create({
  baseURL: '/',
  headers: { 'Content-Type': 'application/json' },
})

export async function fetchUserByUsername(username: string) {
  const endpoint = `/api/auth/getUser/${encodeURIComponent(username)}`
  const response = await apiClient.get(endpoint)
  return response.data
}

