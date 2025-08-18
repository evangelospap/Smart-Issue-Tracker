
import AISummaryCard from "@/components/AISummaryCard";
import { fetchWithAuthServer } from "@/lib/fetchWithAuthServer";

interface IssuePageProps {
  readonly params: { readonly id: string };
}

interface AISummary {
  title: string;
  summary: string;
  aiSuggestion: string;
}

interface Issue {
  id: string;
  title: string;
  description: string;
  createdAt: string;
  summary?: string;
  aiStatus?: string;
}

// page is async because we fetch on the server
export default async function IssuePage({ params }: IssuePageProps) {
  // 1. Fetch issue details
  const issue: Issue = await fetchWithAuthServer(`/api/issues/${params.id}`);

  // 2. Fetch AI summary (separate endpoint, stub if not ready)
  // let aiSummary: { title: string; summary: string; aiSuggestion: string } = {
  //   title: issue.title,
  //   summary: "Generating AI summary...",
  //   aiSuggestion: "AI is thinking...",
  // };
  
  let aiSummary: AISummary = {
    title: issue.title,
    summary: "Generating AI summary...",
    aiSuggestion: "AI is thinking...",
  };
  try {
    aiSummary = await fetchWithAuthServer(`/api/ai/summarize`,
      {
        method: "POST",
        body: JSON.stringify({
          title: issue.title,
          summary: issue.description,
        }),
        headers: {}
      });
    console.log("AI summary fetched successfully:", aiSummary);
  } catch (err) {
    console.error("AI summary fetch failed:", err);
  }

  return (
    <div className="space-y-6">
      <h2 className="text-2xl font-bold">{issue.title}</h2>
      <p className="text-gray-700">{issue.description}</p>
      <p className="text-sm text-gray-500">Created: {issue.createdAt}</p>

      {/* Pass server-fetched summary directly */}
      <AISummaryCard
        title={aiSummary.title}
        summary={aiSummary.summary}
        aiSuggestion={aiSummary.aiSuggestion}
      />
    </div>
  );
}

// smart-issue-tracker-ui/src/app/issues/[id]/page.tsx
