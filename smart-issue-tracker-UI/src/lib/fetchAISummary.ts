// src/lib/fetchAISummary.ts
import { fetchWithAuth } from "./fetchWithAuth";

interface AISummaryResponse {
  summary: string;
  suggestedFix: string;
}

export async function fetchAISummary(issueId: number, title: string, summary: string) {
  return fetchWithAuth("/api/ai/summarize", {
    method: "POST",
    body: JSON.stringify({ title, summary }),
  }) as Promise<AISummaryResponse>;
}
// smart-issue-tracker-ui/src/lib/fetchAISummary.ts