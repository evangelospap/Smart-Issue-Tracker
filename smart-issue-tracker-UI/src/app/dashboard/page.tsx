import IssueCard from "@/components/IssueCard";

const fakeIssues = [
  { id: "1", title: "Login not working", createdAt: "2025-08-17", summary: "Users cannot log in with Google." },
  { id: "2", title: "UI alignment bug", createdAt: "2025-08-16", summary: "Buttons overlap on mobile view." },
];

export default function DashboardPage() {
  return (
    <div className="space-y-4">
      <h2 className="text-2xl font-bold mb-4">Dashboard</h2>
      <div className="grid gap-4">
        {fakeIssues.map((issue) => (
          <IssueCard key={issue.id} {...issue} />
        ))}
      </div>
    </div>
  );
}
// smart-issue-tracker-ui/src/app/dashboard/page.tsx