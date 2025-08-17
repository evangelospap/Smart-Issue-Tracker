// # AI summary component

interface AISummaryCardProps {
  readonly summary?: string;
  readonly suggestedFix?: string;
}

export default function AISummaryCard({ summary, suggestedFix }: AISummaryCardProps) {
  return (
    <div className="p-4 border rounded-lg shadow bg-white">
      <h4 className="font-semibold">AI Summary</h4>
      <p className="text-gray-700">{summary || "Generating AI summary..."}</p>

      <h4 className="font-semibold mt-3">Suggested Fix</h4>
      <p className="text-gray-700">{suggestedFix || "AI is thinking..."}</p>
    </div>
  );
}
