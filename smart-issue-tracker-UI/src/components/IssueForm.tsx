// form to create a new issue
// src/app/dashboard/components/CreateIssueForm.tsx
'use client'

import { useState } from 'react'

export default function IssueForm() {
  const [title, setTitle] = useState('')

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault()
    console.log('Creating issue:', title)
    setTitle('')
  }

  return (
    <form onSubmit={handleSubmit} className="space-y-4 border p-4 rounded-lg">
      <h2 className="text-lg font-semibold">➕ Create New Issue</h2>
      <input
        type="text"
        value={title}
        onChange={e => setTitle(e.target.value)}
        placeholder="Issue title"
        className="w-full p-2 border rounded-md"
      />
      <button type="submit" className="bg-blue-600 text-white px-4 py-2 rounded-md">
        Add Issue
      </button>
    </form>
  )
}