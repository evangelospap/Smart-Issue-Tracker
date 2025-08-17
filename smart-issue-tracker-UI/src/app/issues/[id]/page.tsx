import AISummaryCard from "@/components/AISummaryCard";

interface IssuePageProps {
  readonly params: { readonly id: string };
}

export default function IssuePage({ params }: IssuePageProps) {
  const { id } = params;

  // Fake issue data
  const issue = {
    id,
    title: "Login not working",
    description: "Users report that login with Google is failing with a 500 error.",
    createdAt: "2025-08-17",
  };

  return (
    <div className="space-y-6">
      <h2 className="text-2xl font-bold">{issue.title}</h2>
      <p className="text-gray-700">{issue.description}</p>
      <p className="text-sm text-gray-500">Created: {issue.createdAt}</p>

      {/* AI Summary Section */}
      <AISummaryCard
        summary="The issue seems related to Google OAuth callback misconfiguration."
        suggestedFix="Check OAuth redirect URIs in Google Cloud console and update your .env configuration."
      />
    </div>
  );
}
// smart-issue-tracker-ui/src/app/issues/[id]/page.tsx
