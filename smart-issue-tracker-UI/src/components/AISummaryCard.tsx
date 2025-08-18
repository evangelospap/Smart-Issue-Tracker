"use client";

import { useEffect, useState } from "react";
import { fetchAISummary } from "@/lib/fetchAISummary";

interface AISummaryCardProps {
  title: string;
  summary: string;
  suggestedFix?: string;
}

export default function AISummaryCard({ title, summary }: AISummaryCardProps) {
  const [aiSummary, setAiSummary] = useState<string>();
  const [suggestedFix, setSuggestedFix] = useState<string>();
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    fetchAISummary(0, title, summary)
      .then((res) => {
        setAiSummary(res.summary);
        setSuggestedFix(res.suggestedFix);
      })
      .finally(() => setLoading(false));
  }, [title, summary]);

  return (
    <div className="p-4 border rounded-lg shadow bg-white">
      <h4 className="font-semibold">AI Summary</h4>
      <p className="text-gray-700">{loading ? "Generating AI summary..." : aiSummary}</p>
      <h4 className="font-semibold mt-3">Suggested Fix</h4>
      <p className="text-gray-700">{loading ? "AI is thinking..." : suggestedFix}</p>
    </div>
  );
}
