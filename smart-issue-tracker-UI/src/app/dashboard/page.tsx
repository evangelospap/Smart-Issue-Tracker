import IssueForm from "@/components/IssueForm";
import IssueList from "@/components/IssueList";

export default function DashboardPage() {
  return (
    <div className="space-y-4">
      <h1 className="text-2xl font-bold">📋 Issue Tracker Dashboard</h1>
      {/* <CreateIssueForm /> */}
      <IssueList />
      <IssueForm />
    </div>
  );
}
// smart-issue-tracker-ui/src/app/dashboard/page.tsx