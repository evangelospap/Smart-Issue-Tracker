import IssueForm from "@/components/IssueForm";
import IssueList from "@/components/IssueList";
import { currentUser } from "@clerk/nextjs/server";

export default async function DashboardPage() {
  const user = await currentUser();
  if (!user) {
    return <div className="text-center text-red-500">You must be signed in to view this page.</div>;
  }

  return (
    <div className="space-y-4">
      <h1 className="text-2xl font-bold mb-4">Welcome, {user?.firstName}</h1>
      <h1 className="text-2xl font-bold">📋 Issue Tracker Dashboard</h1>
      <IssueList />
      <IssueForm />
    </div>
  );
}
// smart-issue-tracker-ui/src/app/dashboard/page.tsx