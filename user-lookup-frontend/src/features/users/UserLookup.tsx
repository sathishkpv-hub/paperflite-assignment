import { useState } from 'react'
import type { FormEvent } from 'react'
import { useDispatch, useSelector } from 'react-redux'
import type { AppDispatch, RootState } from '../../store/store'
import { clearUser, getUserByUsername } from './userSlice'

export function UserLookup() {
  const dispatch = useDispatch<AppDispatch>()
  const { data, status, error } = useSelector((state: RootState) => state.user)
  const [username, setUsername] = useState('developer')

  const onSubmit = (e: FormEvent) => {
    e.preventDefault()
    if (!username.trim()) return
    dispatch(getUserByUsername(username.trim()))
  }

  const onClear = () => dispatch(clearUser())

  return (
    <div className="card">
      <form onSubmit={onSubmit} className="row">
        <input
          type="text"
          placeholder="Enter username"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
        />
        <button type="submit" disabled={status === 'loading'}>
          {status === 'loading' ? 'Searching…' : 'Search'}
        </button>
        <button type="button" onClick={onClear} disabled={status === 'loading'}>
          Clear
        </button>
      </form>

      {error && (
        <div style={{ marginTop: '1rem', color: '#b91c1c' }}>Error: {error}</div>
      )}

      {data && (
        <div style={{ marginTop: '1rem' }}>
          <div className="row"><div className="label">Username</div><div className="value">{data.username}</div></div>
          <div className="row"><div className="label">Full Name</div><div className="value">{data.userFullname}</div></div>
          <div className="row"><div className="label">Mobile</div><div className="value">{data.mobileno}</div></div>
          <div className="row"><div className="label">Email</div><div className="value">{data.emailid}</div></div>
          <div className="row"><div className="label">Role</div><div className="value">{data.roleName}</div></div>
          <div className="row"><div className="label">User Type</div><div className="value">{data.userTypeName}</div></div>
          <div className="row"><div className="label">Approved</div><div className="value">{data.isApproved ? 'Yes' : 'No'}</div></div>
          <div className="row"><div className="label">Enabled</div><div className="value">{data.enabled ? 'Yes' : 'No'}</div></div>
          <div className="row"><div className="label">Account Non Locked</div><div className="value">{data.accountNonLocked ? 'Yes' : 'No'}</div></div>
        </div>
      )}
    </div>
  )
}

