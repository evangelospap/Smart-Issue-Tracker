// components/AISummaryCard.tsx
interface AISummaryCardProps {
  title: string;
  summary: string;
  suggestedFix?: string;
}

export default function AISummaryCard({ title, summary, suggestedFix }: AISummaryCardProps) {
  return (
    <div className="p-4 border rounded-lg shadow bg-white">
      <h4 className="font-semibold">Title</h4>
      <p className="text-gray-700">{title}</p>

      <h4 className="font-semibold">AI Summary</h4>
      <p className="text-gray-700">{summary}</p>

      <h4 className="font-semibold mt-3">Suggested Fix</h4>
      <p className="text-gray-700">{suggestedFix}</p>
    </div>
  );
}
