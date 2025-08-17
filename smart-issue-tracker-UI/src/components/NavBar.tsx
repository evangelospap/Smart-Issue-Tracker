// src/components/Navbar.tsx
'use client'

import Link from 'next/link'
import { usePathname } from 'next/navigation'
import AuthenticationBtn from './AuthenticationBtn'

export default function Navbar() {
  const pathname = usePathname()

  return (
    <nav className="flex items-center justify-between px-6 py-4 bg-blue-600 text-white">
      <Link href="/dashboard" className="text-xl font-bold hover:underline">
        🧠 Smart Issue Tracker
      </Link>

      <div className="flex items-center gap-4">
        {pathname.startsWith('/issues/') && (
          <Link href="/dashboard" className="text-sm hover:underline">
            ← Back to Dashboard
          </Link>
        )}

       <AuthenticationBtn />

      </div>
    </nav>
  )
}