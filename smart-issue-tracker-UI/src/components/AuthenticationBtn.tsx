// src/components/AuthenticationBtn.tsx
'use client'

import {
  SignedIn,
  SignedOut,
  SignInButton,
  SignUpButton,
  UserButton,
} from '@clerk/nextjs'

export default function AuthenticationBtn() {
  return (
    <div className="flex items-center gap-4">
      <SignedOut>
        <SignInButton />
        <SignUpButton>
          <button className="bg-white text-blue-600 rounded-full font-medium text-sm px-4 py-2 hover:bg-blue-100">
            Sign Up
          </button>
        </SignUpButton>
      </SignedOut>

      <SignedIn>
        <UserButton />
      </SignedIn>
    </div>
  )
}