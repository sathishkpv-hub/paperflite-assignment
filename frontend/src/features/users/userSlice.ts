import { createAsyncThunk, createSlice } from '@reduxjs/toolkit'
import type { PayloadAction } from '@reduxjs/toolkit'
import type { UserDetails } from './types'
import { fetchUserByUsername } from '../../services/api'

export interface UserState {
  status: 'idle' | 'loading' | 'succeeded' | 'failed'
  error: string | null
  data: UserDetails | null
}

const initialState: UserState = {
  status: 'idle',
  error: null,
  data: null,
}

export const getUserByUsername = createAsyncThunk<UserDetails, string>(
  'user/fetchByUsername',
  async (username, { rejectWithValue }) => {
    try {
      const data = await fetchUserByUsername(username)
      return data as UserDetails
    } catch (error: any) {
      const message = error?.response?.data?.message || error?.message || 'Failed to fetch user'
      return rejectWithValue(message)
    }
  }
)

const userSlice = createSlice({
  name: 'user',
  initialState,
  reducers: {
    clearUser(state) {
      state.data = null
      state.error = null
      state.status = 'idle'
    },
  },
  extraReducers: (builder) => {
    builder
      .addCase(getUserByUsername.pending, (state) => {
        state.status = 'loading'
        state.error = null
      })
      .addCase(getUserByUsername.fulfilled, (state, action: PayloadAction<UserDetails>) => {
        state.status = 'succeeded'
        state.data = action.payload
      })
      .addCase(getUserByUsername.rejected, (state, action) => {
        state.status = 'failed'
        state.error = (action.payload as string) || 'Failed to fetch user'
      })
  },
})

export const { clearUser } = userSlice.actions
export default userSlice.reducer

