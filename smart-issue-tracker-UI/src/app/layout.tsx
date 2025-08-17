import type { Metadata } from 'next'
import {
  ClerkProvider,
  SignInButton,
  SignUpButton,
  SignedIn,
  SignedOut,
  UserButton,
} from '@clerk/nextjs'
import { Inter, Geist, Geist_Mono } from 'next/font/google'
import './globals.css'
import Navbar from '@/components/NavBar'

const geistSans = Geist({
  variable: '--font-geist-sans',
  subsets: ['latin'],
})

const geistMono = Geist_Mono({
  variable: '--font-geist-mono',
  subsets: ['latin'],
})

const inter = Inter({ subsets: ["latin"] });

export const metadata: Metadata = {
  title: "Smart Issue Tracker",
  description: 'AI-powered issue tracking system',
}

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode
}>) {
  return (
    <ClerkProvider>
      <html lang="en">
        <body className={inter.className}>
          <Navbar />
          <main className="min-h-screen flex flex-col">
            <section className="flex-1 p-6">{children}</section>
          </main>
        </body>
      </html>
    </ClerkProvider>
  )
}