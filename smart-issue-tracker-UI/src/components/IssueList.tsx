import IssueCard from "./IssueCard";

export default function IssueList() {

  const issues = [
    { id: 1, title: 'Fix login bug', status: 'Open' },
    { id: 2, title: 'Improve dashboard layout', status: 'In Progress' },
    { id: 3, title: "Login not working", createdAt: "2025-08-17", summary: "Users cannot log in with Google." },
    { id: 4, title: "UI alignment bug", createdAt: "2025-08-16", summary: "Buttons overlap on mobile view." },
  ]

  return (
    <div className="grid gap-4">
      <h2 className="text-lg font-semibold mb-2">🛠️ Issues</h2>
         {issues.map((issue) => (
           <IssueCard key={issue.id} {...issue} />
         ))}
    </div>
  )
}
// smart-issue-tracker-ui/src/components/IssueList.tsx