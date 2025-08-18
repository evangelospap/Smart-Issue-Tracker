
"use client";

import IssueCard from "./IssueCard";
import useSWR from "swr";
import { useAuth } from "@clerk/nextjs";

export default function IssueList() {
  const { getToken } = useAuth();

  const fetcher = async (url: string) => {
    const token = await getToken({ template: "Smart-Issue-Tracker" }); // your backend API template
    const res = await fetch(url, {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });
    if (!res.ok) throw new Error("Failed to fetch issues");
    return res.json();
  };

  const { data: issues, error, mutate } = useSWR("/api/issues", fetcher);

  if (error) return <div className="text-red-500">Failed to load issues</div>;
  if (!issues) return <div>Loading...</div>;

  return (
    <div className="grid gap-4">
      <h2 className="text-lg font-semibold mb-2">🛠️ Issues</h2>
      {issues.length === 0 ? (
        <p>No issues found</p>
      ) : (
        issues.map((issue: any) => <IssueCard key={issue.id} {...issue} />)
      )}
    </div>
  );
}

//   const issues = [
//     { id: 1, title: 'Fix login bug', status: 'Open' },
//     { id: 2, title: 'Improve dashboard layout', status: 'In Progress' },
//     { id: 3, title: "Login not working", createdAt: "2025-08-17", summary: "Users cannot log in with Google." },
//     { id: 4, title: "UI alignment bug", createdAt: "2025-08-16", summary: "Buttons overlap on mobile view." },
//   ]

// smart-issue-tracker-ui/src/components/IssueList.tsx