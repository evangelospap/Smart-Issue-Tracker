// src/components/IssueForm.tsx
"use client";
import { useState } from "react";
import useSWR from "swr";
import { useAuth } from "@clerk/nextjs";

export default function IssueForm() {
  const [title, setTitle] = useState("");
  const [description, setDescription] = useState("");
  const { getToken } = useAuth();
  const { mutate } = useSWR("/api/issues");

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    const token = await getToken({ template: "Smart-Issue-Tracker" });
    const res = await fetch("/api/issues", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${token}`,
      },
      body: JSON.stringify({ title, description }),
    });

    if (res.ok) {
      setTitle("");
      setDescription("");
      mutate();
    } else {
      console.error("Failed to create issue");
    }
  };

  return (
    <form onSubmit={handleSubmit} className="space-y-2">
      <input
        type="text"
        placeholder="Issue title"
        value={title}
        onChange={(e) => setTitle(e.target.value)}
        className="border p-2 rounded w-full"
        required
      />
      <textarea
        placeholder="Issue description"
        value={description}
        onChange={(e) => setDescription(e.target.value)}
        className="border p-2 rounded w-full"
        required
      />
      <button type="submit" className="bg-blue-500 text-white px-4 py-2 rounded">
        Create Issue
      </button>
    </form>
  );
}
// smart-issue-tracker-ui/src/components/IssueForm.tsx