export interface UserDetails {
  userId: number
  userFullname: string
  password: string
  mobileno: number
  emailid: string
  isApproved: number
  userTypeName: string
  userTypeId: number
  roleName: string
  roleId: number
  authorities: unknown
  username: string
  enabled: boolean
  accountNonLocked: boolean
  accountNonExpired: boolean
  credentialsNonExpired: boolean
}

